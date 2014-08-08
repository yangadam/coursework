#pragma once
#include "stdafx.h"
#include "VisibleGameObject.h"


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