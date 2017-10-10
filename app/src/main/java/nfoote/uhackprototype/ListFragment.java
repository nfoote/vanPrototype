package nfoote.uhackprototype;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ListFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    private LinearLayoutManager mLayoutManager;
    private FirebaseRecyclerAdapter<Order, ItemViewHolder> mFireAdapter;


    public ListFragment() {
        // Required empty public constructor
    }

    public RecyclerView mRecyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Add some dummy data to the db */
        //Order order1 = new Order("First");
        //Order order2 = new Order("Second");
        //ref.child("orders").push().setValue(order1);
        //ref.child("orders").push().setValue(order2);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), mLayoutManager.VERTICAL));

         mFireAdapter = new FirebaseRecyclerAdapter<Order, ItemViewHolder>(Order.class,
                R.layout.order_row, ItemViewHolder.class, ref.child("orders")){

            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, Order order, int position) {
                viewHolder.name.setText(order.getName());

                System.out.println("populateViewHolder for position "+position+" with program ");
            }
        };

        mRecyclerView.setAdapter(mFireAdapter);

        return rootView;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.order);
            //mUid = (TextView) itemView.findViewById(R.id.Uid);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mFireAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStart();
        mFireAdapter.startListening();
    }


    public void showPopup(View anchorView) {

        View popupView = getActivity().getLayoutInflater().inflate(R.layout.menu_popout, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT);

        int location[] = new int[2];

        // Example: If you have a TextView inside `popup_layout.xml


        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER,
                location[0], location[1]);

        View container = (View) popupWindow.getContentView().getParent();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);

        ImageButton close = (ImageButton) popupView.findViewById(R.id.imageButtonClose);
        close.setOnClickListener(new View.OnClickListener() {

            public void onClick(View popupView) {
                popupWindow.dismiss();
            }
        });
    }

}
