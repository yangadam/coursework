#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


class Grey:
	public VisibleGameObject
{
public:
	Grey();
	~Grey();
	void draw(sf::RenderWindow&);
	int getType() const;
private:

};