#pragma once

#include "stdafx.hpp"
#include "IAudioProvider.hpp"

class ServiceLocator
{
public:
	static IAudioProvider* GetAudio()  { return _audioProvider; } const

		static void RegisterServiceLocator(IAudioProvider *provider)
	{
		_audioProvider = provider;
	}

private:
	static IAudioProvider * _audioProvider;
};