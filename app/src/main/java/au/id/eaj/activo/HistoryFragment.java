package au.id.eaj.activo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ainsley on 7/10/2017.
 */

public class HistoryFragment extends Fragment {
    View myView;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.history_layout, container, false);
        return myView;
    }
}
