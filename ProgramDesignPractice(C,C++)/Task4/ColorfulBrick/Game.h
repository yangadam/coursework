#pragma once
#include "SFML/Window.hpp"
#include "SFML/Graphics.hpp"
#include "SFML/Audio.hpp"
#include "TimeBar.h"
#include "Brick.h"
#include "BrickManager.h"
#include "Position.h"
#include "Score.h"
#include "Menu.h"
#include "CommonScreen.h"
#include "SFMLSoundProvider.h"
#include "ServiceLocator.h"

class Game
{
public:
	static void start(sf::RenderWindow&);

	const static int SCREEN_WIDTH = 1024;
	const static int SCREEN_HEIGHT = 768;

private:
	static bool isExiting();
	static void gameLoop(sf::RenderWindow&);

	static void showSplashScreen(sf::RenderWindow&);
	static void showMainMenu(sf::RenderWindow&);
	static void showSettings(sf::RenderWindow&);
	static void showHelping(sf::RenderWindow&);
	static void showPausedScreen(sf::RenderWindow&);
	static void showOverScreen(sf::RenderWindow&);


	enum GameState {  Exiting, ShowingSplash, ShowingMainMenu, ShowingSetting,
		ShowingHelping, Playing, Paused, Over};

	static GameState _gameState;
	static BrickManager _bricks;
	static TimeBar _timeBar;
	static sf::Clock _clock;
	static Score _score;
	static bool _isPressed;
	
};

