#include "stdafx.hpp"
#include "Grey.hpp"


Grey::Grey()
{
	load("media/images/Grey.png");
	assert(isLoaded());
}

Grey::~Grey()
{

}

void Grey::draw(sf::RenderWindow& rw)
{
	VisibleGameObject::draw(rw);
}

int Grey::getType() const
{
	return 3;
}