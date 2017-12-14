/********************************************
 Class: AGAtivivityGame
 Description: Android activity class
 Author: Silvano Maneck Malfatti
 Date: 05/11/2013
 ********************************************/

//Engine package
package br.com.hellmets.escapedaextincao.AndGraph;

//Used packages
import android.app.Activity;
import android.os.Bundle;

import br.com.hellmets.escapedaextincao.CenaGame;
import br.com.hellmets.escapedaextincao.CenaAbertura;
import br.com.hellmets.escapedaextincao.CenaGameOver;

public class AGActivityGame extends Activity
{
	//Attributes
	protected AGGameManager vrManager = null;

	/********************************************
	 * Name: AGActivity
	 * Description: constructor
	 * Parameters: Activity, boolean
	 * Returns: none
	 ******************************************/
	public void init(Activity context, boolean accel)
	{
		vrManager = new AGGameManager(context, accel);
	}
	
	/********************************************
	* Name: onCreate()
	* Description: method called to create the application
	* Parameters: Bundle
	* Returns: none
	******************************************/
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		//Alteração feita por mim
		//Criação do gerente da aplicação
		this.vrManager = new AGGameManager(this, true);

		CenaAbertura abertura = new CenaAbertura(vrManager);
        CenaGame game = new CenaGame(vrManager);
        CenaGameOver gameOver = new CenaGameOver(vrManager);

		vrManager.addScene(abertura);
        vrManager.addScene(game);
        vrManager.addScene(gameOver);




	}
  
	/*******************************************
	* Name: onPause()
	* Description: method called to pause application
	* Parameters: none
	* Returns: none
	******************************************/
	protected void onPause()
	{
		super.onPause();
		AGSoundManager.vrMusic.pause();
		vrManager.onPause();
	}
	
	/*******************************************
	* Name: onResume()
	* Description: method called after resume application
	* Parameters: none
	* Returns: none
	******************************************/
	protected void onResume()
	{
		super.onResume();
		vrManager.onResume();
		AGSoundManager.vrMusic.play();
	}
  
	/*******************************************
	* Name: onBackPressed()
	* Description: method called when Android back button is pressed
	* Parameters: none
	* Returns: none
	*****************************************/
	public void onBackPressed()
	{
		AGInputManager.vrTouchEvents.bBackButtonClicked = true;
	}
  
	/*******************************************
	* Name: onDestroy()
	* Description: method called when applicatin is destroyed
	* Parameters: none
	* Returns: none
	*****************************************/
	protected void onDestroy()
	{
		super.onDestroy();
		vrManager.release();
		vrManager = null;
		System.gc();
	}
}