#include "stdafx.h"
#include "Lightblue.h"


Lightblue::Lightblue()
{
	load("images/Lightblue.png");
	assert(isLoaded());
}

Lightblue::~Lightblue()
{

}

void Lightblue::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Lightblue::getType() const
{
	return 4;
}