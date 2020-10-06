#include "stdafx.hpp"
#include "Orange.hpp"


Orange::Orange()
{
	load("media/images/Orange.png");
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