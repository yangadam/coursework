#include "stdafx.h"
#include "Grey.h"


Grey::Grey()
{
	load("images/Grey.png");
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