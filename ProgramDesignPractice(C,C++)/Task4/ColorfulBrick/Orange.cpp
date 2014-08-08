#include "stdafx.h"
#include "Orange.h"


Orange::Orange()
{
	load("images/Orange.png");
	assert(isLoaded());
}

Orange::~Orange()
{

}

void Orange::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Orange::getType() const
{
	return 7;
}