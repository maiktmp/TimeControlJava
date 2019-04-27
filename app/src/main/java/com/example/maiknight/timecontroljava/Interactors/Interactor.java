package com.example.maiknight.timecontroljava.Interactors;

import com.example.maiknight.timecontroljava.Database.DBTime;
import com.example.maiknight.timecontroljava.Database.daos.GroupDao;
import com.example.maiknight.timecontroljava.Database.daos.UserDao;
import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.api.ApiTime;
import com.example.maiknight.timecontroljava.async.RxInstructions;
import com.example.maiknight.timecontroljava.utils.callbacks.CBGeneric;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Interactor {
    private ApiTime api;
    private static Interactor instance;
    private UserDao userDao = DBTime.db.userDao();
    private GroupDao groupDao = DBTime.db.groupDao();

    public Interactor() {
        api = ApiTime.getInstance();
    }

    public static Interactor getInstance() {
        if (instance == null) {
            instance = new Interactor();
        }
        return instance;
    }

    public void getUserGroups(long userId, CBGeneric<List<Group>> cb) {
        api.getGruoups(userId, (success, list) -> {
            if (!success) {
                cb.onResult(null);
            }
            RxInstructions.completable(() -> {
                        Group[] groups = new Group[list.size()];
                        list.toArray(groups);
                        groupDao.upsert(groups);
                    },
                    () -> cb.onResult(list));
        });
    }

}
