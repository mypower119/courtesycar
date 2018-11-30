package vn.mtouch.courtesycar.presentation.base_mvp;

/**
 * Created by 14617 on 22/1/2018.
 */

public interface BaseView<T> {
    default void setPresenter(T presenter) {

    }
}
