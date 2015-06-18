package com.hwj4477.androidlib.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sqlite Wrapper
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.12.
 *
 */

public class SqliteWrapper {
		
	private SQLiteDatabase db = null;

    /**
     * Constructor : Create sqlite file.
     *
     * @param dirPath
     * @param fileName
     */
	public SqliteWrapper(String dirPath, String fileName)
	{
		File file = new File(dirPath);
		if(!file.exists())
			file.mkdirs();
		
		String dbFilePath = dirPath + "/" + fileName;
		
		db = SQLiteDatabase.openOrCreateDatabase(dbFilePath, null);
	}

    /**
     * Constructor : Init with assets file.
     *
     * @param context
     * @param dirPath
     * @param fileName
     * @param assetsFile
     */
    public SqliteWrapper(Context context, String dirPath, String fileName, String assetsFile)
	{
		File file = new File(dirPath);
		if(!file.exists())
			file.mkdirs();
		
		String dbFilePath = dirPath + "/" + fileName;
		file = new File(dbFilePath);
		if(!file.exists())
		{
			try 
			{
				InputStream is = context.getAssets().open(assetsFile);
				OutputStream os = new FileOutputStream(dbFilePath);
				
				byte[] buffer = new byte[1024];
				int len;
				while((len = is.read(buffer)) > 0)
					os.write(buffer, 0, len);
				
				os.flush();
				os.close();
				is.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try
		{
			db = SQLiteDatabase.openDatabase(dbFilePath, null, SQLiteDatabase.OPEN_READWRITE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}

    @SuppressLint("NewApi")
    public ArrayList<HashMap<String, Object>> selectQuery(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
    {
        Cursor cursor = db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        if (cursor.moveToFirst())
        {
            ArrayList<HashMap<String, Object>> arrayResult = new ArrayList<HashMap<String, Object>>();

            do
            {
                HashMap<String, Object> data = new HashMap<String, Object>();

                for(int i=0; i<cursor.getColumnCount(); i++)
                {
                    switch (cursor.getType(i))
                    {
                        case Cursor.FIELD_TYPE_INTEGER:
                            data.put(cursor.getColumnName(i), cursor.getInt(i));
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            data.put(cursor.getColumnName(i), cursor.getFloat(i));
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            data.put(cursor.getColumnName(i), cursor.getString(i));
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            data.put(cursor.getColumnName(i), cursor.getBlob(i));
                            break;
                        default:
                            break;
                    }
                }

                arrayResult.add(data);

            }while (cursor.moveToNext());

            cursor.close();

            return arrayResult;
        }
        else
        {
            return null;
        }
    }

	public ArrayList<HashMap<String, Object>> selectQuery(String query)
	{
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) 
		{
			ArrayList<HashMap<String, Object>> arrayResult = new ArrayList<HashMap<String, Object>>();
			
			do
			{
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				for(int i=0; i<cursor.getColumnCount(); i++)
				{
					switch (cursor.getType(i)) 
					{
					case Cursor.FIELD_TYPE_INTEGER:
						data.put(cursor.getColumnName(i), cursor.getInt(i));
						break;
					case Cursor.FIELD_TYPE_FLOAT:
						data.put(cursor.getColumnName(i), cursor.getFloat(i));
						break;
					case Cursor.FIELD_TYPE_STRING:
						data.put(cursor.getColumnName(i), cursor.getString(i));
						break;
					case Cursor.FIELD_TYPE_BLOB:
						data.put(cursor.getColumnName(i), cursor.getBlob(i));
						break;
					default:
						break;
					}
				}
				
				arrayResult.add(data);
				
			}while (cursor.moveToNext());
			
			cursor.close();
			
			return arrayResult;
		} 
		else 
		{
			return null;
		}
	}
	
	public boolean insert(String table, String nullColumnHack, ContentValues values)
	{
		return db.insert(table, nullColumnHack, values) != -1;
	}
	
	public boolean update(String table, ContentValues values, String whereClause, String[] whereArgs)
	{
		return db.update(table, values, whereClause, whereArgs) > 0;
	}
	
	public boolean delete(String table, String whereClause, String[] whereArgs)
	{
		return db.delete(table, whereClause, whereArgs) > 0;
	}
	
	public void execSQL(String query)
	{	
		db.execSQL(query);
	}
}
