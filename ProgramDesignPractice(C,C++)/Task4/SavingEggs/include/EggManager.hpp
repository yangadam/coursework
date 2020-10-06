#pragma once
#include "GameObjectManager.hpp"
#include "stdafx.hpp"

class EggManager:
	public GameObjectManager
{
public:
	void updateAll(float);
};