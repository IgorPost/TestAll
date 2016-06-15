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
import java.text.SimpleDateFormat;

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

        long iD = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_ID.name));

        long date = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_DATE.name));
        String strNomenclature = cursor.getString(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_NOMENCLATURE.name));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_QUANTITY.name));
        long price = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_PRICE.name));
        long amount = cursor.getLong(cursor.getColumnIndexOrThrow(CasheDatabaseHelper.TablePurchases.COLUMN_AMOUNT.name));

        DecimalFormat df = new DecimalFormat("##,##0,00");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        TextView tvID = (TextView) view.findViewById(R.id.tvID);
        tvID.setText(String.valueOf(iD));

        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvDate.setText(String.valueOf(dateFormat.format(date)));

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
