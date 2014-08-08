#include "stdafx.h"
#include "Lightpink.h"


Lightpink::Lightpink()
{
	load("images/Lightpink.png");
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