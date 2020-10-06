#include "stdafx.hpp"
#include "Purple.hpp"


Purple::Purple()
{
	load("media/images/Purple.png");
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