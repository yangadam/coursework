#include "stdafx.h"
#include "Game.h"

int main(int argc, char** argv)
{
	srand(time((time_t*)NULL));
	sf::RenderWindow mainWindow(sf::VideoMode(1024, 768, 32), "Colorful Brick");
	mainWindow.setKeyRepeatEnabled(false);
	Game::start(mainWindow);
	
	return EXIT_SUCCESS;
}

