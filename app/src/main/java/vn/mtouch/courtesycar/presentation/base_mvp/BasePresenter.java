package vn.mtouch.courtesycar.presentation.base_mvp;

/**
 * Created by 14617 on 22/1/2018.
 */

public interface BasePresenter<V> {
    void attachView(V view);
    void detachView();
    boolean isViewAttached();
}
