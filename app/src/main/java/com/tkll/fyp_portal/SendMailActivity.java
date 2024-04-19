package com.tkll.fyp_portal;

import android.content.Context; // Import Context class
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class SendMailActivity extends Fragment {
    EditText editTxtSubject, editTxtContent, editTxtRecipientMail;
    ImageButton sendMail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_send_email, container, false);

        editTxtSubject = (EditText)rootView.findViewById(R.id.emailSubject);
        editTxtContent = (EditText)rootView.findViewById(R.id.emailContent);
        editTxtRecipientMail= (EditText)rootView.findViewById(R.id.toMail);
        sendMail = (ImageButton)rootView.findViewById(R.id.sendMail);

        sendMail.setOnClickListener(v -> {
            String subject, content, recipient_address;
            subject = editTxtSubject.getText().toString();
            content = editTxtContent.getText().toString();
            recipient_address = editTxtRecipientMail.getText().toString();

            if (!isValidEmail(recipient_address)) {
                Toast.makeText(getContext(), "Invalid email address format!",
                        Toast.LENGTH_SHORT).show();
            } else {
                if (content.equals("") || recipient_address.equals("")) {
                    Toast.makeText(getContext(), "Email's content and recipient's email address cannot be left empty!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    sendEmail(getContext(), subject, content, recipient_address);
                }
            }



        });

        return rootView;
    }

    public void sendEmail(Context context,
                          String subject,
                          String content,
                          String recipient_address) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient_address});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, "Send email via: "));
        } else {
            Toast.makeText(context, "No available email client is found on your device.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}