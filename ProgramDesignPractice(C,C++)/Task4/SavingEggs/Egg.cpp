#include "stdafx.h"
#include "Egg.h"
#include "Game.h"


Egg::Egg()
{
	_eggState = Drop;
	load("images/ball.png");
	assert(isLoaded());

	getSprite().setOrigin(15,15);
	//getSprite().setPosition(Game::SCREEN_WIDTH/2, Game::SCREEN_HEIGHT/2);

}

Egg::~Egg()
{

}

void Egg::update(float elapsedTime)
{
	if (_eggState == Drop)
	{
		float moveAmount = _velocity  * elapsedTime;
		getSprite().move(0,moveAmount);
	}
	if (getPosition().y >= 598 || _counter > 15)
	{
		if (getPosition().y >= 598)
		{
			Game::_gameState = Game::GameOver;
		}
		else
		{
			Game::_gameState = Game::Win;
		}
		getSprite().setPosition(getSprite().getPosition().x, -rand()%200-10);
		if (abs(getPosition().x - 80) < 1e-6)
		{
			Game::_cooks.setState(0, CookManager::Bad);
		} 
		else if (abs(getPosition().x - 240) < 1e-6)
		{
			Game::_cooks.setState(1, CookManager::Bad);
		}
		else
		{
			Game::_cooks.setState(2, CookManager::Bad);
		}
		for (int i=0; i<3; i++)
		{
			Game::op[i] = 1;
		}
		_eggState = Stop;
		_counter = 3;
	}
		
}

void Egg::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

bool Egg::isDropping()
{
	if (_eggState == Drop)
	{
		return true;
	}
	return false;
}

void Egg::setState(bool isDropping)
{
	if (isDropping)
	{
		//getSprite().setPosition(getSprite().getPosition().x, y);
		_eggState = Drop;
		_counter++;

	}
	else
	{
		_eggState = Stop;
	}
}

void Egg::setVelocity(float velocity)
{
	_velocity = velocity;
}

float Egg::getVelocity()
{
	return _velocity;
}

unsigned Egg::_counter = 3;
float Egg::_velocity = 200;