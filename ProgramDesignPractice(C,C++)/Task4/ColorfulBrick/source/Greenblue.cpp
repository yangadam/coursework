#include "stdafx.hpp"
#include "Greenblue.hpp"


Greenblue::Greenblue()
{
	load("media/images/Greenblue.png");
	assert(isLoaded());
}

Greenblue::~Greenblue()
{

}

void Greenblue::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Greenblue::getType() const
{
	return 2;
}