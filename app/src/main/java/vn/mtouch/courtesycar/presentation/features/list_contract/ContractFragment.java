package vn.mtouch.courtesycar.presentation.features.list_contract;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.mtouch.courtesycar.CourtesyCarApp;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;
import vn.mtouch.courtesycar.presentation.features.list_car.ListCarFragment;
import vn.mtouch.courtesycar.presentation.features.ui_utils.TransferActivity;
import vn.mtouch.courtesycar.utils.AndroidUtilities;

import static android.app.Activity.RESULT_OK;

public class ContractFragment extends Fragment {
    @BindView(R.id.list)
    RecyclerView rvContracts;
    @BindView(R.id.imgScanCode)
    View fabAddContracts;
    @BindView(R.id.sv_search_order)
    SearchView searchView;

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
        initObserve("");
        setupSearchView();
    }

    public void initObserve(String query) {
        if(mViewModel.getContracts() != null && mViewModel.getContracts().hasObservers()) {
            mViewModel.getContracts().removeObservers(this);
        }
        mViewModel.init(query);
        mViewModel.getContracts().observe(this, items -> {
            mAdapter.setItems(items);
        });
    }

    private void setupEvent() {
        fabAddContracts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(TransferActivity.getCallingIntent(getActivity(), null));
                final IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity()) {
                    @Override
                    protected void startActivityForResult(Intent intent, int code) {
                        ContractFragment.this.startActivityForResult(intent, code);
                    }
                };
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt(getActivity().getResources().getString(R.string.scanning_barcode));
                intentIntegrator.initiateScan();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanningResult != null && resultCode == RESULT_OK) {
                String code = scanningResult.getContents();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<BorrowContractModel> lst = mViewModel.findContactBorrowingByQrcode(code);
                        if(lst.size() > 0) {
                            // nếu tìm được thì show
                            AndroidUtilities.getsUIHandler().post(() -> {
                                startActivity(TransferActivity.getCallingIntent(getActivity(), lst.get(0), false));
                            });
                        } else {
                            // nếu không có thì tạo 1 cái object bỏ vào startActivity
                            List<CarModel> cars = mViewModel.findCarByQrCode(code);
                            if(cars.size() > 0 ) {
                                BorrowContractModel borrowContractModel = new BorrowContractModel();
                                borrowContractModel.setQrCode(cars.get(0).getQrCode());
                                borrowContractModel.setCarCode(cars.get(0).getCarCode());
                                borrowContractModel.setCarName(cars.get(0).getCarName());
                                borrowContractModel.setTimeIn(Calendar.getInstance().getTimeInMillis());
                                borrowContractModel.setState(BorrowContractModel.STATE_NEW_BORROW);

                                AndroidUtilities.getsUIHandler().post(() -> {
                                    startActivity(TransferActivity.getCallingIntent(getActivity(), borrowContractModel, true));
                                    Toast.makeText(CourtesyCarApp.getAppContext(), "", Toast.LENGTH_LONG).show();
                                });
                            } else {
                                AndroidUtilities.getsUIHandler().post(() -> {
                                    Toast.makeText(CourtesyCarApp.getAppContext(), "QRcode not found", Toast.LENGTH_LONG).show();
                                });
                            }
                        }
                    }
                }).start();
            }
        }
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconified(false);
        searchView.setFocusable(false);
        searchView.clearFocus();
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                onFilterQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                onFilterQuery(query);
                return false;
            }
        });
    }

    public void onFilterQuery(String query) {
        try {
            initObserve(query);
            ((LinearLayoutManager) rvContracts.getLayoutManager()).scrollToPositionWithOffset(0, 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
