package com.hwj4477.androidlib.av;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * MediaPlayer Wrapper
 * 
 * @author hwj4477@gmail.com
 * @since 13.9.10.
 *
 */

public class MediaPlayerWrapper {
	
	private static MediaPlayerWrapper instance = null;
	
	private Context _context = null;
	
	private MediaPlayer player = null;

	/**
	 * Sington
	 *
	 * @param context
	 * @return instance
	 */
	synchronized public static MediaPlayerWrapper getInstance(Context context)
	{
		if(instance == null)
			instance = new MediaPlayerWrapper(context);
		return instance;
	}

	private MediaPlayerWrapper(Context context)
	{
		_context = context;
	}

	/**
	 * Play Sound (from resource ID)
	 *
	 * @param resid
	 * @param listener
	 */
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

	/**
	 * Play Sound (from File Path)
	 *
	 * @param filepath
	 * @param listener
	 */
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

	/**
	 * Play Sound (from File Uri)
	 *
	 * @param fileuri
	 * @param listener
	 */
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

	/**
	 * Stop
	 */
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

	/**
	 * Pause
	 */
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

	/**
	 * Resume
	 */
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

	/**
	 * Helper.
	 *
	 */
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
}
