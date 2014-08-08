#include "stdafx.h"
#include "Golden.h"


Golden::Golden()
{
	load("images/Golden.png");
	assert(isLoaded());
}

Golden::~Golden()
{

}

void Golden::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Golden::getType() const
{
	return 1;
}