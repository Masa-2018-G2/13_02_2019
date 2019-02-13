package com.sheygam.masa_2018_g2_13_02_19;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyRowListener {
    private RecyclerView myRv;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRv = findViewById(R.id.my_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager manager = new GridLayoutManager(this,3);
//        adapter = new MyAdapter(this);
        MessageAdapter msgAdapter = new MessageAdapter();
        myRv.setLayoutManager(manager);
        myRv.setAdapter(msgAdapter);

        DividerItemDecoration divider = new DividerItemDecoration(this, manager.getOrientation());
        myRv.addItemDecoration(divider);
//        ItemTouchHelper helper = new ItemTouchHelper(new MyTouchCallback());
//        helper.attachToRecyclerView(myRv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_item) {
            adapter.add(new User("NewUser", "mewuser"));
        } else if (item.getItemId() == R.id.delete_item) {
            adapter.remove(3);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "Click " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position) {
        Toast.makeText(this, "Long click " + position, Toast.LENGTH_SHORT).show();
    }

    class MyTouchCallback extends ItemTouchHelper.Callback {

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.DOWN|ItemTouchHelper.UP, ItemTouchHelper.END | ItemTouchHelper.START);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder curr, @NonNull RecyclerView.ViewHolder target) {
            Log.d("MY_TAG", "onMove: ");
            adapter.move(curr.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            final int position = viewHolder.getAdapterPosition();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Remove?")
                    .setMessage("Remove this item?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.remove(position);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.notifyItemChanged(position);
                        }
                    })
                    .create()
                    .show();

        }
    }
}
