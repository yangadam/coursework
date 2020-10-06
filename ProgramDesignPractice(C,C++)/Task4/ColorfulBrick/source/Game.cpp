#include "Game.hpp"

void Game::start(sf::RenderWindow& rw)
{
	SFMLSoundProvider soundProvider;
	ServiceLocator::RegisterServiceLocator(&soundProvider);

	soundProvider.PlaySong("media/audio/Soundtrack.ogg",true);

	_gameState= Game::ShowingSplash;

	while(!isExiting())
	{
		gameLoop(rw);
	}

	rw.close();
}

bool Game::isExiting()
{
	if(_gameState == Game::Exiting) 
		return true;
	else 
		return false;
}


void Game::gameLoop(sf::RenderWindow& rw)
{
	sf::Event currentEvent;

	switch(_gameState)
	{
	case Game::ShowingSplash:
		{
			showSplashScreen(rw);
			break;
		}
	case Game::ShowingMainMenu:
		{
			showMainMenu(rw);
			break;
		}
	case Game::ShowingSetting:
		{
			
			showSettings(rw);
			break;
		}
	case Game::ShowingHelping:
		{
			showHelping(rw);
			break;
		}
	case Game::Playing:
		{
			rw.pollEvent(currentEvent);
			sf::Mouse mouse;
			if(currentEvent.type == sf::Event::Closed)
				_gameState = Game::Exiting;
			if(currentEvent.type == sf::Event::KeyPressed)
			{
				if(currentEvent.key.code == sf::Keyboard::Escape)
				{
					showMainMenu(rw);
					break;
				}

				if(currentEvent.key.code == sf::Keyboard::Pause)
				{
					_gameState = Paused;
					break;
				}
			}
			sf::Vector2f cusorPos((float)mouse.getPosition(rw).x, (float)mouse.getPosition(rw).y);
			Position pos(cusorPos);
			sf::Texture texture;
			sf::Sprite sprite;
			if (_bricks[pos.getIndex()] == NULL)
			{
				texture.loadFromFile("media/images/Darkgrey.png");
				sprite.setTexture(texture);
				sprite.setPosition(pos.getTopLeft());
			}
			if (mouse.isButtonPressed(sf::Mouse::Left))
			{
				if (!_isPressed)
				{
					_score += _bricks.handleClick(pos);
				}
				_isPressed = true;
			}
			else
			{
				_isPressed = false;
			}


			rw.clear(sf::Color::White);

			_score.draw(rw, sf::Vector2f(800, 0), 30);
			if(_timeBar.isOver(rw, _clock.getElapsedTime().asSeconds()))
				_gameState = Over;
			_bricks.updateAll(_clock.getElapsedTime().asSeconds());
			_bricks.drallAll(rw);

			rw.draw(sprite);

			rw.display();


			_clock.restart();
			break;
		}
	case Paused:
		{
			showPausedScreen(rw);
			break;
		}
	case Over:
		{
			showOverScreen(rw);
			_gameState = ShowingMainMenu;
			break;
		}
	}
}

void Game::showSplashScreen(sf::RenderWindow& rw)
{
	CommonScreen splashScreen;
	splashScreen.show(rw, "media/images/SplashScreen.png");
	_gameState = Game::ShowingMainMenu;
}

void Game::showMainMenu(sf::RenderWindow& rw)
{
	Menu mainMenu;
	Menu::MenuResult result = mainMenu.show(rw, "media/images/mainmenu.png");
	switch(result)
	{
	case Menu::Button1:
		_bricks.initialize();
		_timeBar.reset();
		_score.reset();
		_clock.restart();
		_gameState = Playing;
		break;
	case Menu::Button2:
		_gameState = ShowingSetting;
		break;
	case Menu::Button3:
		_gameState = ShowingHelping;
		break;
	case Menu::Button4:
		_gameState = Exiting;
		break;	
	}
}

void Game::showSettings(sf::RenderWindow& rw)
{
	Menu settingMenu;
	Menu::MenuResult result = settingMenu.show(rw, "media/images/settings.png");
	
	switch(result)
	{
	case Menu::Button1:
		_bricks.setRate(16);
		_timeBar.setTotalTime(45);
		break;
	case Menu::Button2:
		_bricks.setRate(14);
		_timeBar.setTotalTime(55);
		break;
	case Menu::Button3:
		_bricks.setRate(12);
		_timeBar.setTotalTime(65);
		break;
	case Menu::Button4:
		_gameState = ShowingMainMenu;
		break;	
	}
	
}

void Game::showHelping(sf::RenderWindow& rw)
{
	CommonScreen helpingScreen;
	helpingScreen.show(rw, "media/images/help.png");
	_gameState = Game::ShowingMainMenu;
}

void Game::showPausedScreen(sf::RenderWindow& rw)
{
	CommonScreen pausedScreen;
	pausedScreen.show(rw, "media/images/pause.png");
	_clock.restart();
	_gameState = Playing;
}

void Game::showOverScreen(sf::RenderWindow& rw)
{
	sf::Texture texture;
	if(!texture.loadFromFile("media/images/score.png"))
	{
		return;
	}


	sf::Sprite sprite(texture);

	rw.draw(sprite);
	_score.draw(rw, sf::Vector2f(150,320), 120);
	rw.display();

	sf::Event event;
	while(true)
	{
		
		rw.pollEvent(event);
		if(event.type == sf::Event::KeyPressed || event.type == sf::Event::Closed
			|| (event.type == sf::Event::MouseButtonPressed && event.mouseButton.x<980 
			&& event.mouseButton.x>580 && event.mouseButton.y>600 && event.mouseButton.y<680))
		{
			_gameState = ShowingMainMenu;
			_timeBar.reset();
			return;
		}
	}

}

Game::GameState Game::_gameState = ShowingSplash;
BrickManager Game::_bricks;
TimeBar Game::_timeBar;
sf::Clock Game::_clock;
Score Game::_score;
bool Game::_isPressed = false;
