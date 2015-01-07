package com.hwj4477.androidlib.av;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 * @update 13.11.12.
 *
 */

public class MediaPlayerWrapper {
	
	private static MediaPlayerWrapper instance = null;
	
	private Context _context = null;
	
	private MediaPlayer player = null;
	
	private MediaPlayerWrapper(Context context)
	{
		_context = context;
	}
	
	synchronized public static MediaPlayerWrapper getInstance(Context context)
	{
		if(instance == null)
			instance = new MediaPlayerWrapper(context);
		return instance;
	}

	// Play Resource
	public void playSound(int resid, MediaPlayer.OnCompletionListener listener)
	{
		try
        {
			player = MediaPlayer.create(_context, resid);
			player.setOnCompletionListener(listener);
			player.start();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	// Play file
	public void playSound(String filepath, MediaPlayer.OnCompletionListener listener)
	{
		try
        {
			player = new MediaPlayer();
			player.setOnCompletionListener(listener);
			player.setDataSource(filepath);
			player.prepare();
			player.start();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	public void playSound(Uri fileuri, MediaPlayer.OnCompletionListener listener)
	{
		try
        {
			player = MediaPlayer.create(_context, fileuri);
			player.setOnCompletionListener(listener);;
			player.start();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	public int getCurrentTime()
	{
		return player.getCurrentPosition() / 1000;
	}
	
	public int getDuration()
	{
		return player.getDuration() / 1000;
	}
	
	public void setCurrentTime(int time)
	{
		player.seekTo(time * 1000);
	}
	
	synchronized public void stop()
	{
		try
		{
			if(player != null)
			{
				if(player.isPlaying())
				{
					player.stop();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(player != null)
			{
				player.release();
				player = null;
			}
		}
	}
	
	synchronized public void pause()
	{
		try
		{
			if(player != null)
			{
				if(player.isPlaying())
				{
					player.pause();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	synchronized public void resume()
	{
		try
		{
			if(player != null)
			{
				if(!player.isPlaying())
				{
					player.start();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
