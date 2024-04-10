package com.tkll.fyp_portal;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel model) {
        holder.supervisor.setText(model.getSupervisor());
        holder.projectTitle.setText(model.getProjectTitle());
        holder.description.setText(model.getDescription());

        Glide.with(holder.img.getContext())
                .load(model.getImagePath())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.supervisor.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();
                View view=dialogPlus.getHolderView();

                EditText supervisor=view.findViewById(R.id.txtSupervisorName);
                EditText projecttitle=view.findViewById(R.id.txtProjectTitle);
                EditText description=view.findViewById(R.id.txtDescription);
                EditText expectedoutcome=view.findViewById(R.id.txtExpectedOutcome);
                EditText areaofstudy=view.findViewById(R.id.txtAreaOfStudy);
                EditText skillsrequired=view.findViewById(R.id.txtSkillsRequired);
                EditText nostudent=view.findViewById(R.id.txtNoStudents);
                EditText equipment=view.findViewById(R.id.txtEquipment);
                EditText suitablefor=view.findViewById(R.id.txtSuitableFor);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                supervisor.setText(model.getSupervisor());
                projecttitle.setText(model.getProjectTitle());
                description.setText(model.getDescription());
                expectedoutcome.setText(model.getExpectedOutcome());
                areaofstudy.setText(model.getAreaOfStudy());
                skillsrequired.setText(model.getSkillsRequired());
                nostudent.setText(model.getNoStudents());
                equipment.setText(model.getEquipment());
                suitablefor.setText(model.getSuitableFor());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("Supervisor",supervisor.getText().toString());
                        map.put("ProjectTitle",projecttitle.getText().toString());
                        map.put("Description",description.getText().toString());
                        map.put("ExpectedOutcome",expectedoutcome.getText().toString());
                        map.put("AreaOfStudy",areaofstudy.getText().toString());
                        map.put("SkillsRequired",skillsrequired.getText().toString());
                        map.put("NoStudents",nostudent.getText().toString());
                        map.put("Equipment",equipment.getText().toString());
                        map.put("SuitableFor",suitablefor.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Offered Titles")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.supervisor.getContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                                        Toast.makeText(holder.supervisor.getContext(),"Error While Updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }


                });


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.supervisor.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data cannot undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Offered Titles")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.supervisor.getContext(),"Cancelled.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView supervisor,projectTitle,description;

        Button btnEdit,btnDelete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            supervisor = (TextView) itemView.findViewById(R.id.SupervisorText);
            projectTitle=(TextView) itemView.findViewById(R.id.ProjectTitleText);
            description=(TextView) itemView.findViewById(R.id.DescriptionText);

            btnEdit =(Button) itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}

