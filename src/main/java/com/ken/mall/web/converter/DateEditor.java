package com.ken.mall.web.converter;

import com.ken.mall.constant.Constants;
import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.xfbetter.web.common.converter
 * author Daniel
 * 2017/12/21.
 */
public class DateEditor extends PropertyEditorSupport {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private boolean emptyAsNull;

    private String dateFormat = DEFAULT_DATE_FORMAT;

    public DateEditor(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
    }

    public DateEditor(boolean emptyAsNull, String dateFormat) {
        this.emptyAsNull = emptyAsNull;
        this.dateFormat = dateFormat;
    }

    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return value != null ? new SimpleDateFormat(dateFormat).format(value) : "";
    }

    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            String value = text.trim();
            if (emptyAsNull && "".equals(value)) {
                setValue(null);
            } else {
                try {
                    setValue(DateUtils.parseDate(value, Constants.DATE_PATTERNS));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        }
    }
}
