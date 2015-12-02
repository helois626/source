package com.ally.pam.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.ally.pam.R;
import com.ally.pam.activities.ChatActivity;
import com.ally.pam.adapter.PaymentAdapter;

/**
 * Created by Ally on 12/1/2015.
 */

public class SlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static SlidePageFragment create(int pageNumber) {
        SlidePageFragment fragment = new SlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView ivRating;

    private Button btnPay;
    private Button btnChat;

    private ListView lvPayment;

    private ImageButton imageButton;

    private String[] NAME = {"PERSONAL", "WORK"};
    private String[] COST = {"*****999", "*****999"};
    private int[] IMAGE = {R.mipmap.visa_card, R.mipmap.credit_card};

    public SlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_slide, container, false);

        ivRating = (ImageView) rootView.findViewById(R.id.ratingBar);
        ivRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(getActivity());
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.setContentView(R.layout.rating_dialog);
                imageButton = (ImageButton) dialog1.findViewById(R.id.cancel_Imagebutton);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.show();
            }
        });

        btnChat = (Button) rootView.findViewById(R.id.request_chat_button);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.validate_proposal);
                imageButton = (ImageButton) dialog.findViewById(R.id.cancel_Imagebutton);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                lvPayment = (ListView) dialog.findViewById(R.id.pay_listview);
                lvPayment.setAdapter(new PaymentAdapter(getActivity().getApplicationContext(), NAME, IMAGE, COST));
                dialog.show();
            }
        });

        btnPay = (Button) rootView.findViewById(R.id.request_pay_button);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat = new Intent(getActivity().getBaseContext(), ChatActivity.class);
                startActivity(chat);
            }
        });

        // Set the title view to show the page number.
//        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
//                getString(R.string.title_template_step, mPageNumber + 1));

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
