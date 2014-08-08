#include "stdafx.h"
#include "Cook.h"
#include "Game.h"

Cook::Cook(std::string str)
{
	load(str);
	assert(isLoaded());
	//getSprite().setOrigin(15,15);
	//getSprite().setPosition(Game::SCREEN_WIDTH/2, Game::SCREEN_HEIGHT/2);
}

Cook::~Cook()
{

}

void Cook::update(float noUse)
{

}

void Cook::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

