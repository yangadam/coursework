#include "stdafx.h"
#include "Purple.h"


Purple::Purple()
{
	load("images/Purple.png");
	assert(isLoaded());
}

Purple::~Purple()
{

}

void Purple::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Purple::getType() const
{
	return 8;
}