#include "stdafx.h"
#include "Game.h"
#include "MainMenu.h"
#include "SplashScreen.h"
#include "Egg.h"
#include "Cook.h"
//»¹Ã»Ð´ºÃ#include "settings.h"


void Game::start(sf::RenderWindow& rw)
{

	Egg* pegg[3] = {new Egg, new Egg, new Egg};
	pegg[0]->setPosition(80, -rand()%200-10);
	pegg[1]->setPosition(240, -rand()%200-10);
	pegg[2]->setPosition(400, -rand()%200-10);
	_eggs.add("egg0",pegg[0]);
	_eggs.add("egg1",pegg[1]);
	_eggs.add("egg2",pegg[2]);

	Cook* pcook[9] = {new Cook("images/wait.png"), new Cook("images/wait.png"), new Cook("images/wait.png"),
		new Cook("images/good.png"),new Cook("images/good.png"),new Cook("images/good.png"),
		new Cook("images/bad.png"),new Cook("images/bad.png"),new Cook("images/bad.png"),};
	pcook[0]->setPosition(10, 500);
	pcook[1]->setPosition(170, 500);
	pcook[2]->setPosition(330, 500);
	pcook[3]->setPosition(10, 500);
	pcook[4]->setPosition(170, 500);
	pcook[5]->setPosition(330, 500);
	pcook[6]->setPosition(10, 500);
	pcook[7]->setPosition(170, 500);
	pcook[8]->setPosition(330, 500);
	_cooks.add("wait0",pcook[0]);
	_cooks.add("wait1",pcook[1]);
	_cooks.add("wait2",pcook[2]);
	_cooks.add("good0",pcook[3]);
	_cooks.add("good1",pcook[4]);
	_cooks.add("good2",pcook[5]);
	_cooks.add("bad0",pcook[6]);
	_cooks.add("bad1",pcook[7]);
	_cooks.add("bad2",pcook[8]);

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




//const sf::Input Game::getInput() 
//{
//	return rw.GetInput();
//}

//const GameObjectManager& Game::GetGameObjectManager()
//{
//	return _gameObjectManager;
//}

void Game::gameLoop(sf::RenderWindow& rw)
{
	sf::Event currentEvent;
	rw.pollEvent(currentEvent);


	switch(_gameState)
	{
	case Game::ShowingMenu:
		{
			showMenu(rw);
			break;
		}
	case Game::ShowingSplash:
		{
			showSplashScreen(rw);
			break;
		}
	case Game::Playing:
		{
			sf::Texture texture;
			texture.loadFromFile("images/pause-button.png");
			sf::Sprite sprite(texture);
			sprite.setPosition(470,10);
			sf::Mouse mouse;
			_clock.restart();
			if(currentEvent.type == sf::Event::Closed)
				_gameState = Game::Exiting;
			if(currentEvent.type == sf::Event::KeyPressed)
			{
				if(currentEvent.key.code == sf::Keyboard::Escape)
					showMenu(rw);

				if(currentEvent.key.code == sf::Keyboard::Space)
					_gameState = Paused;


				if(currentEvent.key.code == sf::Keyboard::A && (_eggs.get("egg0"))->getPosition().y > 0)
				{
					if ((_eggs.get("egg0"))->getPosition().y < 420)
						_score += 1;
					else
						_score += ((_eggs.get("egg0"))->getPosition().y - 380)/20;
					(_eggs.get("egg0"))->setPosition(80, -rand()%200-10);
					((Egg*)(_eggs.get("egg0")))->setState(false);
					_cooks.setState(0, CookManager::Good);
					op[0] = 2;

				}
				if(currentEvent.key.code == sf::Keyboard::S && (_eggs.get("egg1"))->getPosition().y > 0)
				{
					if ((_eggs.get("egg1"))->getPosition().y < 420)
						_score += 1;
					else
						_score += ((_eggs.get("egg1"))->getPosition().y - 380)/20;
					(_eggs.get("egg1"))->setPosition(240, -rand()%200-10);
					((Egg*)(_eggs.get("egg1")))->setState(false);
					_cooks.setState(1, CookManager::Good);
					op[1] = 2;
				}
				if(currentEvent.key.code == sf::Keyboard::D && (_eggs.get("egg2"))->getPosition().y > 0)
				{
					if ((_eggs.get("egg2"))->getPosition().y < 420)
						_score += 1;
					else
						_score += ((_eggs.get("egg2"))->getPosition().y - 380)/20;
					(_eggs.get("egg2"))->setPosition(400, -rand()%200-10);
					((Egg*)(_eggs.get("egg2")))->setState(false);
					_cooks.setState(2, CookManager::Good);
					op[2] = 2;
				}
			}
			if (currentEvent.type == sf::Event::MouseButtonPressed)
			{
				if (sprite.getGlobalBounds().contains(mouse.getPosition(rw).x, mouse.getPosition(rw).y))
				{
					_gameState = Paused;
				}
			}
			
			

			rw.clear(sf::Color::White);
			_background.show(rw, op[0], op[1], op[2]);
			_eggs.updateAll(_clock.getElapsedTime().asSeconds());
			_eggs.drawAll(rw);
			_cooks.updateAll(_clock.getElapsedTime().asSeconds());
			_cooks.drawAll(rw);
			_score.draw(rw);
			rw.draw(sprite);

			rw.display();


			
			break;
		}
	case Paused:
		{
			sf::Texture texture;
			texture.loadFromFile("images/pause.png");
			sf::Sprite sprite(texture);
			rw.draw(sprite);
			rw.display();
				if(currentEvent.type == sf::Event::KeyPressed 
					|| currentEvent.type == sf::Event::MouseButtonPressed
					|| currentEvent.type == sf::Event::Closed )
				{
					_gameState = Playing;
					break;
				}
			_clock.restart();
			break;
		}
	case GameOver:
		{
			(_eggs.get("egg0"))->setPosition(80, -rand()%200-10);
			(_eggs.get("egg1"))->setPosition(240, -rand()%200-10);
			(_eggs.get("egg2"))->setPosition(400, -rand()%200-10);
			sf::Texture texture;
			texture.loadFromFile("images/lose.png");
			sf::Sprite sprite(texture);
			rw.draw(sprite);
			rw.display();
			if(currentEvent.type == sf::Event::KeyPressed 
				|| currentEvent.type == sf::Event::MouseButtonPressed
				|| currentEvent.type == sf::Event::Closed )
			{
				_gameState = ShowingMenu;
				((Egg*)(_eggs.get("egg0")))->setVelocity(150);
				_score.reset();
				_clock.restart();
			}
			break;
		}
	case Win:
		{
			sf::Texture texture;
			texture.loadFromFile("images/score.png");
			sf::Sprite sprite(texture);
			rw.draw(sprite);
			_score.draw(rw);
			rw.display();
			if(currentEvent.type == sf::Event::KeyPressed 
				|| currentEvent.type == sf::Event::MouseButtonPressed
				|| currentEvent.type == sf::Event::Closed )
			{
				_gameState = ShowingMenu;
				((Egg*)(_eggs.get("egg0")))->setVelocity(150);
				_score.reset();
				_clock.restart();
			}
			break;
		}
	case Help:
		{
			sf::Texture texture;
			texture.loadFromFile("images/help0.png");
			sf::Sprite sprite(texture);
			rw.draw(sprite);
			rw.display();
			if(currentEvent.type == sf::Event::KeyPressed 
				|| currentEvent.type == sf::Event::MouseButtonPressed
				|| currentEvent.type == sf::Event::Closed )
			{
				_gameState = ShowingMenu;
				_score.reset();
				_clock.restart();
			}
			break;
		}
	case Setting:
		{
			showSettingScreen(rw);
			break;
		}
	}
}

void Game::showSplashScreen(sf::RenderWindow& rw)
{
	SplashScreen splashScreen;
	splashScreen.show(rw);
	_gameState = Game::ShowingMenu;
}

void Game::showMenu(sf::RenderWindow& rw)
{
	MainMenu mainMenu;
	MainMenu::MenuResult result = mainMenu.Show(rw);
	switch(result)
	{
	case MainMenu::Exit:
		_gameState = Exiting;
		break;
	case MainMenu::Play:
		_gameState = Playing;
		break;
	case MainMenu::Help:
		_gameState = Help;
		break;
	case MainMenu::Settings:
		_gameState = Setting;
		break;
	}
}


void Game::showSettingScreen(sf::RenderWindow&rw)
{
	Settings settings;
	Settings::SettingResult result = settings.Show(rw);
	switch(result)
	{
	case Settings::l1:
		Egg::setVelocity(200);
		_gameState = ShowingMenu;
		break;
	case Settings::l2:
		Egg::setVelocity(250);
		_gameState = ShowingMenu;
		break;
	case Settings::l3:
		Egg::setVelocity(350);
		_gameState = ShowingMenu;
		break;
	case Settings::Back:
		_gameState = ShowingMenu;
	}
}
