/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.mtouch.courtesycar.presentation.features.backup_restore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.widget.DataBufferAdapter;

import java.text.SimpleDateFormat;

import vn.mtouch.courtesycar.R;

/**
 * A DataBufferAdapter to display the results of file listing/querying requests.
 */
public class ResultsAdapter extends DataBufferAdapter<Metadata> {
    OnFileAdapterListener mListener;
    public interface OnFileAdapterListener {
        void deleteFile(DriveFile file);
        void restore(DriveFile file);
    }

    public ResultsAdapter(Context context, OnFileAdapterListener listener) {
        super(context, android.R.layout.simple_list_item_1);
        mListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item_file_google_drive, null);
        }
        Metadata metadata = getItem(position);
        TextView titleTextView = convertView.findViewById(R.id.tv_name);
        TextView titleTextFolderNameView = convertView.findViewById(R.id.tv_folder);
        ImageView imgMore = convertView.findViewById(R.id.img_more);
        imgMore.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(getContext(), v);
            popupMenu.inflate(R.menu.menu_action_item_backup);
            popupMenu.setOnMenuItemClickListener((item) -> {
                switch (item.getItemId()) {
                    case R.id.action_restore:
                        //handle menu1 click
                        mListener.restore(metadata.getDriveId().asDriveFile());
                        break;
                    case R.id.action_delete:
                        mListener.deleteFile(metadata.getDriveId().asDriveFile());
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
        titleTextView.setText(metadata.getTitle() + " " + SimpleDateFormat.getInstance().format(metadata.getCreatedDate()));
//        titleTextFolderNameView.setText(metadata.getDriveId().asDriveResource().getDriveId().encodeToString());
        return convertView;
    }
}