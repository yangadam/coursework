#pragma once
#include "stdafx.hpp"
#include "VisibleGameObject.hpp"


class Golden:
	public VisibleGameObject
{
public:
	Golden();
	~Golden();
	void draw(sf::RenderWindow&);
	int getType() const;

private:

};