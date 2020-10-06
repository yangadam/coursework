#pragma once
#include "GameObjectManager.hpp"

class CookManager:
	public GameObjectManager
{
public:
	enum CookState
	{
		Wait, Good, Bad
	}_state0, _state1, _state2;

	CookManager();
	void drawAll(sf::RenderWindow&);
	void setState(int, CookState);

};