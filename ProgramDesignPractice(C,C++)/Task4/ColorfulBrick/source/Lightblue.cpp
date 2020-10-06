#include "stdafx.hpp"
#include "Lightblue.hpp"


Lightblue::Lightblue()
{
	load("media/images/Lightblue.png");
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