#include "stdafx.h"
#include "Greenblue.h"


Greenblue::Greenblue()
{
	load("images/Greenblue.png");
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