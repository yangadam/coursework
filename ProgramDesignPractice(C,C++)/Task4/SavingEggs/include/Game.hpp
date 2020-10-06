#pragma once
#include "SFML/Window.hpp"
#include "SFML/Graphics.hpp"
#include "SFML/Audio.hpp"
#include "Background.hpp"
#include "EggManager.hpp"
#include "CookManager.hpp"
#include "Score.hpp"
#include "Settings.hpp"
#include "Egg.hpp"

class Game
{
	static bool isExiting();
	static void gameLoop(sf::RenderWindow&);

	static void showSplashScreen(sf::RenderWindow&);
	static void showMenu(sf::RenderWindow&);
	static void showSettingScreen(sf::RenderWindow&);
	static void showHelpScreen(sf::RenderWindow&);

	static sf::Clock _clock;

public:
	enum GameState { ShowingSplash, Paused, 
		ShowingMenu, Playing, Exiting, GameOver, Win, Help, Setting};
	static GameState _gameState;
	static Background _background;
	static EggManager _eggs;
	static CookManager _cooks;
	static Score _score;
	const static int SCREEN_WIDTH = 500;
	const static int SCREEN_HEIGHT = 768;

	static void start(sf::RenderWindow&);
	static int op[3];
	
};

