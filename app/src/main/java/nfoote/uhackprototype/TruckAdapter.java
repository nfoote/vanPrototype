package nfoote.uhackprototype;

/**
 * Created by nfoote on 8/09/2017.
 */

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;


public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.MyViewHolder> {

    private final ClickListener listener;
    private List<Trucks> truckList;

    public TruckAdapter(List<Trucks> truckList, ClickListener listener) {
        this.truckList = truckList;
        this.listener = listener;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list, parent, false);

        //return new MyViewHolder(itemView);

        */

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list, parent, false), listener);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Trucks truck = truckList.get(position);
        holder.title.setText(truck.getTitle());
        holder.type.setText(truck.getType());
        holder.menu.setTag(truck.getMenu());

    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView title, type;
        public ImageView menu;
        private WeakReference<ClickListener> listenerRef;

        public MyViewHolder(View view, ClickListener listener) {
            super(view);



            listenerRef = new WeakReference<>(listener);

            title = (TextView) view.findViewById(R.id.title);
            //type = (TextView) view.findViewById(R.id.type);
            //menu = (ImageView) view.findViewById(R.id.menu);


            title.setOnClickListener(this);
            type.setOnClickListener(this);
            menu.setOnLongClickListener(this);


        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {

            if (v.getId() == type.getId()) {
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        //onLongClickListener for view
        @Override
        public boolean onLongClick(View v) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Hello Dialog")
                    .setMessage("LONG CLICK DIALOG WINDOW FOR ICON " + String.valueOf(getAdapterPosition()))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            builder.create().show();
            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }
    }

}