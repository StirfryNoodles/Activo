package au.id.eaj.activo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Vector;

/**
 * Created by Ainsley on 7/10/2017.
 */

public class EarnFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.earn_layout, container, false);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
