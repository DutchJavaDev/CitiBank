package Db;

/**
 * Created by boris on 16-9-2019.
 * Java WIZ XD
 */
public class DatabaseResult {

    private final int LastInsertedId;
    private final boolean Succes;

    public DatabaseResult(int id,boolean succes)
    {
        LastInsertedId = id;
        Succes = succes;
    }

    public int getLastInsertedId() {
        return LastInsertedId;
    }

    public boolean isSucces() {
        return Succes;
    }
}
