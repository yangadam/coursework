#include "stdafx.h"
#include "Lightgreen.h"


Lightgreen::Lightgreen()
{
	load("images/Lightgreen.png");
	assert(isLoaded());
}

Lightgreen::~Lightgreen()
{

}

void Lightgreen::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Lightgreen::getType() const
{
	return 5;
}