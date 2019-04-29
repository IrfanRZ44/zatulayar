package com.zlayar.zlayar.chat;

import com.zlayar.zlayar.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 07/09/2018.
 */

public interface GetUsersContract {
    interface View {
        void onGetAllUsersSuccess(ArrayList<User> users);

        void onGetAllUsersFailure(String message);

        void onGetChatUsersSuccess(ArrayList<User> users);

        void onGetChatUsersFailure(String message);
    }

    interface Presenter {
        void getAllUsers();

        void getChatUsers();
    }

    interface Interactor {
        void getAllUsersFromFirebase();

        void getChatUsersFromFirebase();
    }

    interface OnGetAllUsersListener {
        void onGetAllUsersSuccess(ArrayList<User> users);

        void onGetAllUsersFailure(String message);
    }

    interface OnGetChatUsersListener {
        void onGetChatUsersSuccess(ArrayList<User> users);

        void onGetChatUsersFailure(String message);
    }
}
