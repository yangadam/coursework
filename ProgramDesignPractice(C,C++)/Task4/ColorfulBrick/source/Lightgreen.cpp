#include "stdafx.hpp"
#include "Lightgreen.hpp"


Lightgreen::Lightgreen()
{
	load("media/images/Lightgreen.png");
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