#include "stdafx.hpp"
#include "Lightpink.hpp"


Lightpink::Lightpink()
{
	load("media/images/Lightpink.png");
	assert(isLoaded());
}

Lightpink::~Lightpink()
{

}

void Lightpink::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Lightpink::getType() const
{
	return 6;
}