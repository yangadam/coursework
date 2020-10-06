#include "stdafx.hpp"
#include "Golden.hpp"


Golden::Golden()
{
	load("media/images/Golden.png");
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