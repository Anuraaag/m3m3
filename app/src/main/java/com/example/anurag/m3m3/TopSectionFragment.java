package com.example.anurag.m3m3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class TopSectionFragment extends Fragment {

    private static EditText topTextInput;
    private static EditText bottomTextInput;

    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void generateMeme(String Top, String bottom);  // it's like the main activity promises to create a method generateMeme in itself

        public void createPopup();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activityCommander = (TopSectionListener) context; // creating the interface betweeen main activity and this fragment
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_section_fragment, container, false);

        topTextInput = view.findViewById(R.id.topTextInput);
        bottomTextInput = view.findViewById(R.id.bottomTextInput);
        final Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        final Button edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommander.createPopup();
            }
        });

        return view;
    }

    public void buttonClicked(View v)
    {
        //keyboard disappearing when this component is focused upon

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        //if (null != getContext().getCurrentFocus())
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);


        //the activityCommander calls the method generateMeme() present in MainActivity.java
        activityCommander.generateMeme(topTextInput.getText().toString(), bottomTextInput.getText().toString());
    }
}


//    public interface TopSectionListener {
//        public void creatememe(String top, String bottom);
//    }
//        TopSectionListener activityCommander;
//
//   -> In order to communicate with the Activity (MainActivity in this case), an interface TopSectionListener and a variable of it activityCommander are created;
//   -> TopSectionListener here is then data type (interface) and can be used to declare its own variables like activityCommander.
//   -> This interface is attached to the activity (MainActivity) where the fragment sits, using onAttach()
//   -> When button is clicked, a method defined in the interface is called;
//   -> This method is implemented in the MainActivity; MainActivity receives the call and carries out the method (called by the TopSectionFragment)



//    An enclosing class is exactly what it sounds like - it's a class that encloses (not inherits) the class at the given statement. In order to reference the enclosing class instance, the this keyword must be prefixed with the class name - hence MainActivity.this.
//
//      class ABC {
//         class XYZ extends Activity {
//          }
//      }
//
// In the simple example above, ABC is the enclosing class of XYZ.
// Your error is telling you that MainActivity is not an enclosing class at the statement location, and so the this instance of that class cannot be accessed.
// Your MainActivity2 class inherits from MainActivity, but there is no enclosing class at the Intent(...) statement. Since the Intent() constructor requires a Context parameter and your MainActivity2 this instance inherits from Context (Context -> Activity -> MainActivity -> MainActivity2), you can just use this as the parameter:
//
// So instead of:
//
//  i = new Intent( MainActivity.this, MainActivity2.class);
//
// use:
//
//  i = new Intent(this, MainActivity2.class);
