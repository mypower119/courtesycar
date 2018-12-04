package vn.mtouch.courtesycar.presentation.features.list_contract;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.presentation.features.ui_utils.TransferActivity;

public class ContractFragment extends Fragment {
    @BindView(R.id.list)
    RecyclerView rvContracts;
    @BindView(R.id.fab_add_contract)
    FloatingActionButton fabAddContracts;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 4;
    private ContractAdapter mAdapter;
    private ContractViewModel mViewModel;
    private Unbinder mUnbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContractFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContractFragment newInstance(int columnCount) {
        ContractFragment fragment = new ContractFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract_list, container, false);

        mUnbinder = ButterKnife.bind(this, view);
        mViewModel = ViewModelProviders.of(this).get(ContractViewModel.class);
        mViewModel.init();
        setupUi();
        setupEvent();
        return view;
    }

    private void setupUi() {
        mAdapter = new ContractAdapter(getActivity(), new ArrayList<>(), mViewModel);
        // Set the adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        if (mColumnCount <= 1) {
            rvContracts.setLayoutManager(linearLayoutManager);
        } else {
            rvContracts.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        }
        rvContracts.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvContracts.getContext(),
                linearLayoutManager.getOrientation());
        rvContracts.addItemDecoration(dividerItemDecoration);
        mViewModel.getContracts().observe(this, items -> {
            mAdapter.setItems(items);
        });
    }

    private void setupEvent() {
        fabAddContracts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TransferActivity.getCallingIntent(getActivity(), null));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
