package vn.mtouch.courtesycar.data.db.local.room;

/**
 * @author thuannv
 * @since 17/08/2017
 */
public interface Mapper<From, To> {
    To map(From from);
}
