package com.example.inalivayko.testall.cashe;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.inalivayko.testall.R;

import java.text.DecimalFormat;

/**
 * Created by i.nalivayko on 16.05.2016.
 */
public class CasheCursorAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CasheCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.cashe_list_item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        int iD = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

        String strNomenclature = cursor.getString(cursor.getColumnIndexOrThrow("NOMENCLATURE"));
        double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("QUANTITY"));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow("PRICE"));
        double amount = cursor.getDouble(cursor.getColumnIndexOrThrow("AMOUNT"));

        DecimalFormat df = new DecimalFormat("##,##0,00");

        TextView tvID = (TextView) view.findViewById(R.id.tvID);
        tvID.setText(Integer.toString(iD));

        Spanned sp = Html.fromHtml("<b>"+strNomenclature+"</b>");

        TextView tvNomenclature = (TextView) view.findViewById(R.id.tvNomenclature);
        tvNomenclature.setText(sp);

        TextView tvNumber = (TextView) view.findViewById(R.id.tvNumber);
        tvNumber.setText("Кол-во: "+String.valueOf(quantity));

        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        tvPrice.setText("Цена: "+df.format(price));

        TextView tvAmount = (TextView) view.findViewById(R.id.tvAmount);
        tvAmount.setText("Сумма: "+df.format(amount));
    }
}
