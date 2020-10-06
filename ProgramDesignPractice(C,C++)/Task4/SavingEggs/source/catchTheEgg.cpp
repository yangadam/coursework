#include "stdafx.hpp"
#include "Game.hpp"
#include "SFML/Graphics.hpp"

Game::GameState Game::_gameState = Playing;
Background Game::_background;
EggManager Game::_eggs;
CookManager Game::_cooks;
sf::Clock Game::_clock;
Score Game::_score;

int Game::op[3] = {1,1,1};

int main(int argc, char** argv)
{
	srand(time(NULL));
	sf::RenderWindow mainWindow(sf::VideoMode(500, 768, 32), "Saving eggs!");

	Game::start(mainWindow);

	return EXIT_SUCCESS;
}

